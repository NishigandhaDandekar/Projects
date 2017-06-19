//
//  ListPlacesTableViewController.m
//  MapsProject
//
//  Created by rt_user on 6/17/17.
//  Copyright © 2017 rt_user. All rights reserved.
//

#import "ListPlacesTableViewController.h"
#import "AppDelegate.h"
#import "ViewController.h"
#import "PlaceInformation.h"
#import <AddressBookUI/AddressBookUI.h>
#import "PlaceInformationTableViewCell.h"
#import <MapKit/MapKit.h>
@interface ListPlacesTableViewController ()

@end

@implementation ListPlacesTableViewController{
    AppDelegate * delegate;
    NSMutableArray<PlaceInformation *> * temp;
    }
-(void)setFilter:(NSString *)filter {
    _filter = filter;
}
- (void)viewDidLoad {
    [super viewDidLoad];
    
    delegate = (AppDelegate*)[[UIApplication sharedApplication] delegate];
    delegate.manager.delegate =self;
    [delegate.manager startUpdatingLocation];
  //  NSLog(@"Location: %@",delegate.currentLocation);
   // NSLog(@"current location %f %f",delegate.currentLocation.coordinate.latitude,delegate.currentLocation.coordinate.longitude);
    temp = [[NSMutableArray<PlaceInformation*> alloc]init];
    MKLocalSearchRequest *request = [[MKLocalSearchRequest alloc] init];
    request.naturalLanguageQuery = _filter;
   // NSLog(@"%@",dele)
    request.region = MKCoordinateRegionMake(CLLocationCoordinate2DMake(delegate.currentLocation.coordinate.latitude, delegate.currentLocation.coordinate.longitude), MKCoordinateSpanMake(0.5, 0.5));
    MKLocalSearch * search = [[MKLocalSearch alloc]initWithRequest:request];
  //  MKLocalSearchResponse * searchResponse;
    [search startWithCompletionHandler:^(MKLocalSearchResponse * _Nullable response, NSError * _Nullable error) {
      //  NSMutableArray <PlaceInformation*> * temp = [[NSMutableArray alloc]init];
        [response.mapItems enumerateObjectsUsingBlock:^(MKMapItem * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
            
            PlaceInformation * place = [[PlaceInformation alloc]init];
            place.name=obj.name;
            NSString * streetAddress = ABCreateStringWithAddressDictionary(obj.placemark.addressDictionary,YES);

            streetAddress = [streetAddress stringByReplacingOccurrencesOfString:@"\n" withString:@" "];
            place.address = streetAddress;
            place.location = obj.placemark.location;
            place.url = obj.url;
            place.location = obj.placemark.location;
       //     NSLog(@"Address %@",place.address);
            [temp  addObject:place];
            [self.tableView reloadData];
        }];

        [self.tableView reloadData];
    }];
   // NSLog(@"HEre");

}
- (void)locationManager:(CLLocationManager *)manager
     didUpdateLocations:(NSArray<CLLocation *> *)locations{
    [delegate.manager stopUpdatingLocation];
    delegate.currentLocation = [locations lastObject];
}

-(void)viewWillAppear:(BOOL)animated {
}
-(void)viewDidAppear:(BOOL)animated {
}
- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
}

-(void)setPlaces:(NSMutableArray<PlaceInformation *> *)places {
    _places = places;
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    if([temp count]>0){
      //  NSLog(@"Places count:%ld",delegate.places.count);
        return [temp count];
    }
    return 0;
}


- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    PlaceInformationTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"placeInformationCell" forIndexPath:indexPath];
    cell.title.text = temp[indexPath.row].name;
    cell.address.text = temp[indexPath.row].address;
    cell.direction.tag = indexPath.row;

    [cell.direction addTarget:self action:@selector(getDirections:) forControlEvents:UIControlEventTouchUpInside];
    return cell;
}
-(void) getDirections : (UIButton*)sender {
    PlaceInformation * placeTemp = temp[sender.tag];
    MKPlacemark *placemark = [[MKPlacemark alloc] initWithCoordinate:placeTemp.location.coordinate addressDictionary:nil] ;
    MKMapItem *mapItem = [[MKMapItem alloc] initWithPlacemark:placemark];
    [mapItem setName:placeTemp.name];
    [mapItem openInMapsWithLaunchOptions:nil];
}
-(void)viewDidDisappear:(BOOL)animated {
   // [delegate.places removeAllObjects];
  //  NSLog(@"view did disappear appear table 2 called");

  //  [delegate.places removeAllObjects];
}
/*
// Override to support conditional editing of the table view.
- (BOOL)tableView:(UITableView *)tableView canEditRowAtIndexPath:(NSIndexPath *)indexPath {
    // Return NO if you do not want the specified item to be editable.
    return YES;
}
*/

/*
// Override to support editing the table view.
- (void)tableView:(UITableView *)tableView commitEditingStyle:(UITableViewCellEditingStyle)editingStyle forRowAtIndexPath:(NSIndexPath *)indexPath {
    if (editingStyle == UITableViewCellEditingStyleDelete) {
        // Delete the row from the data source
        [tableView deleteRowsAtIndexPaths:@[indexPath] withRowAnimation:UITableViewRowAnimationFade];
    } else if (editingStyle == UITableViewCellEditingStyleInsert) {
        // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
    }   
}
*/

/*
// Override to support rearranging the table view.
- (void)tableView:(UITableView *)tableView moveRowAtIndexPath:(NSIndexPath *)fromIndexPath toIndexPath:(NSIndexPath *)toIndexPath {
}
*/

/*
// Override to support conditional rearranging of the table view.
- (BOOL)tableView:(UITableView *)tableView canMoveRowAtIndexPath:(NSIndexPath *)indexPath {
    // Return NO if you do not want the item to be re-orderable.
    return YES;
}
*/
-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    
}
/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
