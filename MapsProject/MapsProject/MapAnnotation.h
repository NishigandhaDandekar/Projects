//
//  MapAnnotation.h
//  MapsProject
//
//  Created by rt_user on 6/18/17.
//  Copyright © 2017 rt_user. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <MapKit/MapKit.h>
@interface MapAnnotation : NSObject<MKAnnotation>
@property (nonatomic, assign) CLLocationCoordinate2D coordinate;
@property (nonatomic, copy) NSString *title;
@property (nonatomic, copy) NSString *subtitle;
@property(nonatomic,copy) NSString * address;

@end
