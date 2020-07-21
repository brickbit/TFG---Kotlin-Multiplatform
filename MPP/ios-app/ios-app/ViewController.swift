//
//  ViewController.swift
//  ios-app
//
//  Created by Roberto García Romero on 19/07/2020.
//  Copyright © 2020 Roberto García Romero. All rights reserved.
//

import UIKit
import MultiPlatformLibrary

class ViewController: UIViewController {
    @IBOutlet weak var testText: UILabel!
    override func viewDidLoad() {
    // ...
    let monument = MonumentMainItemDomain(id: 1, title: "La sagrada familia", geocoordinates: "1-1")
        testText.text = monument.title
    HelloWorld().print()
    IosHelloWorld().print()
  }
}

