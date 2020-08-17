//
//  Navigator.swift
//  ios-app
//
//  Created by Roberto García Romero on 17/08/2020.
//  Copyright © 2020 Roberto García Romero. All rights reserved.
//

import Foundation
import UIKit

func navigateToDetailVC(_ viewController: UIViewController, id: String){
    let detailVC = MonumentDetailViewController()
    detailVC.modalPresentationStyle = .fullScreen
    detailVC.id = id
    viewController.present(detailVC, animated: true, completion: nil)
}
