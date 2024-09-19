//
//  TokenHelper.m
//  flutter_push_plugin
//
//  Created by 杜洁鹏 on 2024/9/19.
//

#import "TokenHelper.h"
#import "TokenEventHandler.h"
#import <UserNotifications/UserNotifications.h>

@implementation TokenHelper
- (void)registerDeviceToken {
        UNUserNotificationCenter *center = [UNUserNotificationCenter currentNotificationCenter];
        [center requestAuthorizationWithOptions:(UNAuthorizationOptionAlert + UNAuthorizationOptionSound + UNAuthorizationOptionBadge)
                              completionHandler:^(BOOL granted, NSError * _Nullable error) {
            if (granted) {
                dispatch_async(dispatch_get_main_queue(), ^{
                    [UIApplication.sharedApplication registerForRemoteNotifications];
                });
            } else {
                
            }
        }];
}

- (void)unRegisterDeviceToken {
    [UIApplication.sharedApplication unregisterForRemoteNotifications];
    [TokenEventHandler.shared clearToken];
}
@end
