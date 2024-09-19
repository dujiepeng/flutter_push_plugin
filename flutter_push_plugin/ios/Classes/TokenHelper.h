//
//  TokenHelper.h
//  flutter_push_plugin
//
//  Created by 杜洁鹏 on 2024/9/19.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface TokenHelper : NSObject
- (void)registerDeviceToken;
- (void)unRegisterDeviceToken;
@end

NS_ASSUME_NONNULL_END
