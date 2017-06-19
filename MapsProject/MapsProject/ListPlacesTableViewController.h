//
//  ListPlacesTableViewController.h
//  MapsProject
//
//  Created by rt_user on 6/17/17.
//  Copyright © 2017 rt_user. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "PlaceInformation.h"
#import <CoreLocation/CoreLocation.h>
@interface ListPlacesTableViewController : UITableViewController<CLLocationManagerDelegate>
@property (strong,nonatomic) NSMutableArray<PlaceInformation *> * places;

@property(weak,nonatomic) NSString * filter;

@end
