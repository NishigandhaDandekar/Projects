//
//  PlaceInformation.h
//  MapsProject
//
//  Created by rt_user on 6/17/17.
//  Copyright © 2017 rt_user. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <CoreLocation/CoreLocation.h>

@interface PlaceInformation : NSObject
@property (strong,nonatomic) NSString * name;
@property(strong,nonatomic)NSString * address;
@property(strong,nonatomic) CLLocation * location;
@property(strong,nonatomic) NSURL * url;
@end
