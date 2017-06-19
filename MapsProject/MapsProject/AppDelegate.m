//
//  AppDelegate.m
//  MapsProject
//
//  Created by rt_user on 6/15/17.
//  Copyright © 2017 rt_user. All rights reserved.
//

#import "AppDelegate.h"
@import GooglePlaces;
@import GooglePlacePicker;
#import <MapKit/MapKit.h>
#import <AddressBookUI/AddressBookUI.h>
@interface AppDelegate ()

@end

@implementation AppDelegate


- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions {
    
        if([GMSPlacesClient provideAPIKey:@"AIzaSyBwAnWWUhYUhYVbXxzR5PehhtP-NBbrZ5w"]) {
            self.placeClient = [GMSPlacesClient sharedClient];
    }
    [GMSServices provideAPIKey:@"AIzaSyBwAnWWUhYUhYVbXxzR5PehhtP-NBbrZ5w"];
    self.manager = [[CLLocationManager alloc]init];
    self.manager.delegate = self;
    [self.manager requestWhenInUseAuthorization];
  //  NSLog(@"current location %f %f",self.currentLocation.coordinate.latitude,self.currentLocation.coordinate.longitude);
    self.places = [[NSMutableArray alloc] init];
    // Override point for customization after application launch.
    return YES;
}

- (void)locationManager:(CLLocationManager *)manager didChangeAuthorizationStatus:(CLAuthorizationStatus)status {
    //   NSLog(@"in request permission");
    if(status == kCLAuthorizationStatusAuthorizedWhenInUse)
    {
        [self.manager startUpdatingLocation];
    }
}
- (void)locationManager:(CLLocationManager *)manager
     didUpdateLocations:(NSArray<CLLocation *> *)locations{
    [self.manager stopUpdatingLocation];
    self.currentLocation = [locations lastObject];
    NSLog(@"Location: %@",self.currentLocation);
    NSLog(@"current location %f %f",self.currentLocation.coordinate.latitude,self.currentLocation.coordinate.longitude);
}

- (void)applicationWillResignActive:(UIApplication *)application {
    // Sent when the application is about to move from active to inactive state. This can occur for certain types of temporary interruptions (such as an incoming phone call or SMS message) or when the user quits the application and it begins the transition to the background state.
    // Use this method to pause ongoing tasks, disable timers, and invalidate graphics rendering callbacks. Games should use this method to pause the game.
}


- (void)applicationDidEnterBackground:(UIApplication *)application {
    // Use this method to release shared resources, save user data, invalidate timers, and store enough application state information to restore your application to its current state in case it is terminated later.
    // If your application supports background execution, this method is called instead of applicationWillTerminate: when the user quits.
}


- (void)applicationWillEnterForeground:(UIApplication *)application {
    // Called as part of the transition from the background to the active state; here you can undo many of the changes made on entering the background.
}


- (void)applicationDidBecomeActive:(UIApplication *)application {
    // Restart any tasks that were paused (or not yet started) while the application was inactive. If the application was previously in the background, optionally refresh the user interface.
}


- (void)applicationWillTerminate:(UIApplication *)application {
    // Called when the application is about to terminate. Save data if appropriate. See also applicationDidEnterBackground:.
}


@end
