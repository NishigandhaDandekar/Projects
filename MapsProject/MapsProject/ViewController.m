//
//  ViewController.m
//  MapsProject
//
//  Created by rt_user on 6/15/17.
//  Copyright © 2017 rt_user. All rights reserved.
//

#import "ViewController.h"
#import "AppDelegate.h"
#import "MapAnnotation.h"
#import <AddressBookUI/AddressBookUI.h>

@interface ViewController ()

@end

@implementation ViewController{
    
    NSMutableArray<MKLocalSearchCompletion*> * searchAutoCompleteResults;
    
}

- (void)viewDidLoad {
    [super viewDidLoad];
    AppDelegate * delegate = (AppDelegate *)[[UIApplication sharedApplication]delegate];
    searchAutoCompleteResults = [[NSArray alloc]init];
    self.mapView.delegate =self;
    self.completer = [[MKLocalSearchCompleter alloc]init];
    self.completer.delegate = self;
      self.completer.region = MKCoordinateRegionMake(CLLocationCoordinate2DMake(delegate.currentLocation.coordinate.latitude, delegate.currentLocation.coordinate.longitude), MKCoordinateSpanMake(0.5, 0.5));
 //   self.completer.region = self.mapView.region;
    self.inputPlace.delegate = self;
    [self.inputPlace addTarget:self action:@selector(inputPlaceEditing) forControlEvents:UIControlEventAllEditingEvents];
//    autoCompleteSearchTable = [[UITableView alloc]initWithFrame:CGRectMake(16, 50, , 120) style:UITableViewStylePlain];
    _autoCompleteSearchTable.hidden = YES;
    _autoCompleteSearchTable.delegate = self;
    _autoCompleteSearchTable.dataSource=self;
    _autoCompleteSearchTable.scrollEnabled =YES;
  
    [self.view addSubview:_autoCompleteSearchTable];

}
-(void) inputPlaceEditing {
    //self.completer.searching;
 //   NSLog(@"Method called String: %@",self.inputPlace.text);
    NSString * input = self.inputPlace.text;
    input = [input stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceCharacterSet]];
    if(searchAutoCompleteResults.count > 0){
        [searchAutoCompleteResults removeAllObjects];
    }

    self.completer.queryFragment = input;
    searchAutoCompleteResults =  [NSMutableArray arrayWithArray:self.completer.results];
    if(searchAutoCompleteResults.count >0){
        for (int i=0;i<searchAutoCompleteResults.count;i++){
            if(searchAutoCompleteResults[i].subtitle.length==0){
                [searchAutoCompleteResults removeObjectAtIndex:i];
                i--;
            }
        }

    }
       if(input.length == 0) {
        [searchAutoCompleteResults removeAllObjects];
        _autoCompleteSearchTable.hidden = YES;
    }
    if(searchAutoCompleteResults.count > 0 ){
        _autoCompleteSearchTable.hidden = NO;
    }
    [_autoCompleteSearchTable reloadData];
    
}

-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    [self.mapView removeAnnotations:self.mapView.annotations];
    self.inputPlace.text = searchAutoCompleteResults[indexPath.row].title;
    _autoCompleteSearchTable.hidden = YES;
    if(searchAutoCompleteResults[indexPath.row].subtitle.length != 0) {
        NSString *location = searchAutoCompleteResults[indexPath.row].subtitle;
        CLGeocoder *geocoder = [[CLGeocoder alloc] init];
        [geocoder geocodeAddressString:location
                     completionHandler:^(NSArray* placemarks, NSError* error){
                         if (placemarks!=nil && placemarks.count >0) {
                             CLPlacemark *topResult = [placemarks objectAtIndex:placemarks.count-1];
//                             MapAnnotation *placemark = [[MapAnnotation alloc] initWithPlacemark:topResult];
                             MapAnnotation * annotation = [[MapAnnotation alloc]init];
                             NSLog(@"%@",topResult);
                             annotation.title =searchAutoCompleteResults[indexPath.row].title;
                             annotation.subtitle = ABCreateStringWithAddressDictionary(topResult.addressDictionary,YES);                         //    NSLog(@"%@",placemark.title);
                             
                             MKCoordinateRegion region;
                             region.center = topResult.location.coordinate;
                             
                             annotation.coordinate = topResult.location.coordinate;
                             region.span.longitudeDelta = 0.02;
                             region.span.latitudeDelta = 0.02;
                             //placemark.title = mark.name;
                             [self.mapView setRegion:region animated:YES];
                             [self.mapView addAnnotation:annotation];
                         }
                     }
         ];
    }

  //  [searchAutoCompleteResults removeAllObjects];
}
- (nullable MKAnnotationView *)mapView:(MKMapView *)mapView viewForAnnotation:(id <MKAnnotation>)annotation {
    MapAnnotation * annotate = (MapAnnotation*)annotation;
    MKPinAnnotationView * annotationView = [[MKPinAnnotationView alloc]initWithAnnotation:annotation reuseIdentifier:@"pin"];
    
    annotationView.canShowCallout = YES;
    annotationView.pinTintColor =[UIColor purpleColor];
    annotationView.frame = CGRectMake(0, 0, 90, 200);
    UIButton * button =[UIButton buttonWithType:UIButtonTypeDetailDisclosure];
    [button setBackgroundImage:[UIImage imageNamed:@"navigate.png"] forState:UIControlStateNormal];
    annotationView.rightCalloutAccessoryView = button;
    
   //  annotationView.frame = CGRectMake(0, 0, 70, 200);
    return annotationView;
    
}
-(void)mapView:(MKMapView *)mapView annotationView:(MKAnnotationView *)view calloutAccessoryControlTapped:(UIControl *)control {
    MapAnnotation * annotation =(MapAnnotation*) view.annotation;
    MKPlacemark *placemark = [[MKPlacemark alloc] initWithCoordinate:annotation.coordinate addressDictionary:nil] ;
    MKMapItem *mapItem = [[MKMapItem alloc] initWithPlacemark:placemark];
    [mapItem setName:annotation.title];
    [mapItem openInMapsWithLaunchOptions:nil];
}
//- (BOOL)textField:(UITextField *)textField
//shouldChangeCharactersInRange:(NSRange)range
//replacementString:(NSString *)string {
//}
- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)textFieldDidBeginEditing:(UITextField *)textField    {
    
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
  //  NSLog(@"Number of Rows %ld",[searchAutoCompleteResults count]);
    return [searchAutoCompleteResults count];
}
-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    UITableViewCell * cell = [[UITableViewCell alloc]initWithStyle:UITableViewCellStyleSubtitle reuseIdentifier:@"cell"];
    cell.textLabel.text = searchAutoCompleteResults[indexPath.row].title;
    cell.detailTextLabel.text = searchAutoCompleteResults[indexPath.row].subtitle;
  //  NSLog(@"Result: %@",searchAutoCompleteResults[indexPath.row]);
  
    return cell;
}
- (void)completerDidUpdateResults:(MKLocalSearchCompleter *)completer {
    
}
- (IBAction)search:(id)sender {
    //  MKLocalSearchRequest * request  = [[MKLocalSearchRequest alloc]init];
}
@end
