//
//  TokenEventHandler.m
//  flutter_push_plugin
//
//  Created by 杜洁鹏 on 2024/9/19.
//

#import "TokenEventHandler.h"

@interface TokenEventHandler ()<FlutterStreamHandler>

@end

@implementation TokenEventHandler

static TokenEventHandler *_instance;

+ (TokenEventHandler *)shared {
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        _instance = [[TokenEventHandler alloc] init];
    });
    return _instance;
}

- (void)bindBinaryMessenger:(NSObject<FlutterBinaryMessenger>*)messenger {
    FlutterEventChannel *channel = [FlutterEventChannel eventChannelWithName:@"token" binaryMessenger:messenger];
    [channel setStreamHandler:self];
}

- (FlutterError * _Nullable)onCancelWithArguments:(id _Nullable)arguments {
    return nil;
}

- (FlutterError * _Nullable)onListenWithArguments:(id _Nullable)arguments eventSink:(nonnull FlutterEventSink)events { 
    self.events = events;
    return nil;
}

- (void)updateDeviceToken:(NSData *)deviceToken {
    self.events([self _extractTokenFromRawData:deviceToken]);
}

- (void)onDeviceTokenError:(NSError *)error {
    FlutterError *flutterError = [FlutterError errorWithCode:[NSString stringWithFormat:@"%ld", (long)error.code]
                                                     message:error.description
                                                     details:[NSString stringWithFormat:@"get ios token err: %@",error.description]];
    self.events(flutterError);
}

- (void)clearToken {
    self.events(@"");
}

- (NSString *)_extractTokenFromRawData:(NSData *)deviceToken
{
    NSString *token = @"";
    do {
        if (@available(iOS 13.0, *)) {
            if ([deviceToken isKindOfClass:[NSData class]]) {
                const unsigned *tokenBytes = (const unsigned *)[deviceToken bytes];
                token = [NSString stringWithFormat:@"%08x%08x%08x%08x%08x%08x%08x%08x",
                                      ntohl(tokenBytes[0]), ntohl(tokenBytes[1]), ntohl(tokenBytes[2]),
                                      ntohl(tokenBytes[3]), ntohl(tokenBytes[4]), ntohl(tokenBytes[5]),
                                      ntohl(tokenBytes[6]), ntohl(tokenBytes[7])];
                break;
            }else if ([deviceToken isKindOfClass:[NSString class]]) {
                token = [NSString stringWithFormat:@"%@",deviceToken];
                token = [token stringByReplacingOccurrencesOfString:@"<" withString:@""];
                token = [token stringByReplacingOccurrencesOfString:@">" withString:@""];
                token = [token stringByReplacingOccurrencesOfString:@" " withString:@""];
                break;
            }
        }else {
            token = [NSString stringWithFormat:@"%@",deviceToken];
            token = [token stringByReplacingOccurrencesOfString:@"<" withString:@""];
            token = [token stringByReplacingOccurrencesOfString:@">" withString:@""];
            token = [token stringByReplacingOccurrencesOfString:@" " withString:@""];
            break;
        }
    } while (0);
    
    return token;
}


@end
