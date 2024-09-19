//
//  NotificationEventHandler.h
//  flutter_push_plugin
//
//  Created by 杜洁鹏 on 2024/9/19.
//

#import <Foundation/Foundation.h>
#import <Flutter/Flutter.h>

NS_ASSUME_NONNULL_BEGIN

@interface NotificationEventHandler : NSObject
@property (nonatomic, copy) FlutterEventSink events;
+ (NotificationEventHandler *)shared;
- (void)bindBinaryMessenger:(NSObject<FlutterBinaryMessenger>*)messenger;
@end

NS_ASSUME_NONNULL_END
