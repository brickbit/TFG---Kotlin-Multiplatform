//
//  MonumentTableViewCell.swift
//  ios-app
//
//  Created by Roberto García Romero on 16/08/2020.
//  Copyright © 2020 Roberto García Romero. All rights reserved.
//

import UIKit
import MultiPlatformLibrary
class MonumentTableViewCell: UITableViewCell {
    @IBOutlet weak var title: UILabel!
    @IBOutlet weak var coordinates: UILabel!
    @IBOutlet weak var id: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
    func linkAttributes(item: MonumentMainItemDomain) {
        title.text = item.title
        coordinates.text = item.geocoordinates
        id.text = String(item.id)
    }
    
}
