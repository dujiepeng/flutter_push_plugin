//
//  TokenEventHandler.h
//  flutter_push_plugin
//
//  Created by 杜洁鹏 on 2024/9/19.
//

#import <Foundation/Foundation.h>
#import <Flutter/Flutter.h>

NS_ASSUME_NONNULL_BEGIN

@interface TokenEventHandler : NSObject
@property (nonatomic, copy) FlutterEventSink events;
+ (TokenEventHandler *)shared;
- (void)bindBinaryMessenger:(NSObject<FlutterBinaryMessenger>*)messenger;
- (void)updateDeviceToken:(NSData *)deviceToken;
- (void)onDeviceTokenError:(NSError *)error;
- (void)clearToken;
@end

NS_ASSUME_NONNULL_END
