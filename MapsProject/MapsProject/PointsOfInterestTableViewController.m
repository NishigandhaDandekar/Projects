//
//  PointsOfInterestTableViewController.m
//  MapsProject
//
//  Created by rt_user on 6/17/17.
//  Copyright © 2017 rt_user. All rights reserved.
//

#import "PointsOfInterestTableViewController.h"
#import "PointsOfInterestCell.h"
#import "ListPlacesTableViewController.h"
#import "AppDelegate.h"
#import "ViewController.h"
#import "PlaceInformation.h"
#import <AddressBookUI/AddressBookUI.h>
#import "PlaceInformationTableViewCell.h"

@interface PointsOfInterestTableViewController ()

@end

@implementation PointsOfInterestTableViewController {
    NSArray * points;
    NSArray * pointImage;
    NSInteger selectedRowIndex;
    AppDelegate * delegate;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    self.navigationItem.title = @"Categories";
    points = @[@"Restaurants",@"Shopping",@"Gas Station",@"Movie Theatre",@"Coffee"];
    pointImage = @[@"reataurant.png",@"shopping-icon.png",@"gas.jpg",@"movie.jpg",@"coffee.png"];
    delegate = (AppDelegate*)[[UIApplication sharedApplication] delegate];
    delegate.places = [[NSMutableArray alloc]init];
    NSLog(@"Location: %@",delegate.currentLocation);
    NSLog(@"current location %f %f",delegate.currentLocation.coordinate.latitude,delegate.currentLocation.coordinate.longitude);
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
}

#pragma mark - Table view data source

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return 1;
}
-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {

}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return [points count];
}


- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    PointsOfInterestCell *cell = [tableView dequeueReusableCellWithIdentifier:@"interest" forIndexPath:indexPath];
    
    cell.displayLabel.text = points[indexPath.row];
    
    cell.imageView.image = [UIImage imageNamed:pointImage[indexPath.row]];
    return cell;
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


#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    if([segue.identifier isEqualToString:@"details"]) {

          NSIndexPath * indexPath = [self.tableView indexPathForSelectedRow];
 
        ListPlacesTableViewController * destination = (ListPlacesTableViewController*)[segue destinationViewController];
            destination.navigationItem.title = points[indexPath.row];
        destination.filter  = points[indexPath.row];
                NSLog(@"Prepare for segue");
}
    
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}


@end
