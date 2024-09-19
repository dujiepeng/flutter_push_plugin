//
//  NotificationEventHandler.m
//  flutter_push_plugin
//
//  Created by 杜洁鹏 on 2024/9/19.
//

#import "NotificationEventHandler.h"

@interface NotificationEventHandler ()<FlutterStreamHandler>

@end

@implementation NotificationEventHandler

static NotificationEventHandler *_instance;

+ (NotificationEventHandler *)shared {
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        _instance = [[NotificationEventHandler alloc] init];
    });
    return _instance;
}

- (void)bindBinaryMessenger:(NSObject<FlutterBinaryMessenger>*)messenger {
    FlutterEventChannel *channel = [FlutterEventChannel eventChannelWithName:@"openNotification" binaryMessenger:messenger];
    [channel setStreamHandler:self];
}

- (FlutterError * _Nullable)onCancelWithArguments:(id _Nullable)arguments { 
    return nil;
}

- (FlutterError * _Nullable)onListenWithArguments:(id _Nullable)arguments eventSink:(nonnull FlutterEventSink)events { 
    self.events = events;
    return nil;
}

@end
