//
//  PlaceInformationTableViewCell.h
//  
//
//  Created by rt_user on 6/17/17.
//
//

#import <UIKit/UIKit.h>
#import <CoreLocation/CoreLocation.h>

@interface PlaceInformationTableViewCell : UITableViewCell
@property (weak, nonatomic) IBOutlet UIButton *direction;
@property (weak, nonatomic) IBOutlet UILabel *title;
@property (weak, nonatomic) IBOutlet UILabel *address;
@property (assign,nonatomic) CLLocationCoordinate2D * location;
@property (weak,nonatomic) NSURL * url;
- (IBAction)information:(id)sender;

- (IBAction)navigate:(id)sender;
@end
