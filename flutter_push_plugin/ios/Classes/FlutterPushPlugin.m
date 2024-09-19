#import "FlutterPushPlugin.h"
#import "TokenEventHandler.h"
#import "NotificationEventHandler.h"
#import "TokenHelper.h"

@interface FlutterPushPlugin ()
@property (nonatomic, strong) TokenEventHandler *tokenHandler;
@property (nonatomic, strong) NotificationEventHandler *notificationHandler;
@property (nonatomic, strong) TokenHelper *tokenHelper;
@end

@implementation FlutterPushPlugin

+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
    FlutterMethodChannel* channel = [FlutterMethodChannel
                                     methodChannelWithName:@"flutter_push_plugin"
                                     binaryMessenger:[registrar messenger]];
    FlutterPushPlugin *plugin = [[FlutterPushPlugin alloc] initWithBinaryMessenger:[registrar messenger]];
    [registrar addMethodCallDelegate:plugin channel:channel];
}


- (instancetype)initWithBinaryMessenger:(NSObject<FlutterBinaryMessenger>*)messenger { 
    if(self = [super init]) {
        self.tokenHandler = TokenEventHandler.shared;
        [self.tokenHandler bindBinaryMessenger:messenger];
        self.notificationHandler = NotificationEventHandler.shared;
        [self.notificationHandler bindBinaryMessenger:messenger];
        self.tokenHelper = [[TokenHelper alloc] init];
        return self;
    }
    return nil;
}


- (void)handleMethodCall:(FlutterMethodCall*)call result:(FlutterResult)result {
    if ([@"init" isEqualToString:call.method]) {
        [self.tokenHelper registerDeviceToken];
    } else if ([@"unInit" isEqualToString:call.method]) {
        [self.tokenHelper unRegisterDeviceToken];
    } else {
        result(FlutterMethodNotImplemented);
    }
}

@end

@interface FlutterAppDelegate (Push)

@end

@implementation FlutterAppDelegate(Push)
- (void)application:(UIApplication *)application didRegisterForRemoteNotificationsWithDeviceToken:(NSData *)deviceToken {
    [TokenEventHandler.shared updateDeviceToken:deviceToken];
}

- (void)application:(UIApplication *)application didFailToRegisterForRemoteNotificationsWithError:(NSError *)error {
    [TokenEventHandler.shared onDeviceTokenError:error];
}
@end
