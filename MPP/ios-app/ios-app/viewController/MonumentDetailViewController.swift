//
//  MonumentDetailViewController.swift
//  ios-app
//
//  Created by Roberto García Romero on 17/08/2020.
//  Copyright © 2020 Roberto García Romero. All rights reserved.
//

import UIKit
import MultiPlatformLibrary

class MonumentDetailViewController: BaseViewController<DetailView>, DetailView {
    @IBOutlet weak var titleMonument: UILabel!
    @IBOutlet weak var descriptionMonument: UILabel!
    @IBOutlet weak var addressMonument: UILabel!
    @IBOutlet weak var coordinatesMonument: UILabel!
    @IBOutlet weak var phoneMonument: UILabel!
    
    var id = ""
    let di: AppDI = AppDI()
        
    lazy var presenter: MonumentDetailPresenter = MonumentDetailPresenter(
        repository: di.repository,
        errorHandler: di.errorHandler,
        executor: di.executor,
        view: self
    )
    
    override func initializeUI() {
        // Nothing to do yet
    }
    
    override func getPresenter() -> BasePresenter<DetailView>? {
        return presenter
    }
    
    override func registerListeners() {
        // Nothing to do yet
    }
    func getId() -> String {
        let res = Int(id)! + 1
        return String(res)
    }
    
    @IBAction func back(_ sender: Any) {
        self.dismiss(animated: true, completion: nil)
    }
    
    func showMonumentDetail(monumentDetailDomain: MonumentDetailDomain) {
        titleMonument.text = monumentDetailDomain.title
        descriptionMonument.text = monumentDetailDomain.description()
        addressMonument.text = monumentDetailDomain.address
        coordinatesMonument.text = monumentDetailDomain.geocoordinates
        phoneMonument.text = monumentDetailDomain.phone
    }

}
