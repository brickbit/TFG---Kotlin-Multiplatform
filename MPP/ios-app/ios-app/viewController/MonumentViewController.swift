//
//  ViewController.swift
//  ios-app
//
//  Created by Roberto García Romero on 23/07/2020.
//  Copyright © 2020 Roberto García Romero. All rights reserved.
//

import UIKit
import MultiPlatformLibrary

class MonumentViewController: BaseViewController<MonumentView>, MonumentView, UITableViewDelegate, UITableViewDataSource {
    private var listItems: [MonumentMainItemDomain] = []
    @IBOutlet weak var monumentList: UITableView!
    private let monumentIdentifier = String(describing: MonumentTableViewCell.self)

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return listItems.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: monumentIdentifier) as! MonumentTableViewCell
        cell.linkAttributes(item: listItems[indexPath.row])
        return cell
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 60.0
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        navigateToDetailVC(self,id: String(indexPath.row))
    }
    
    
    let di: AppDI = AppDI()
        
    lazy var presenter: MonumentPresenter = MonumentPresenter(
        repository: di.repository,
        errorHandler: di.errorHandler,
        executor: di.executor,
        view: self
    )
    
    override func initializeUI() {
        monumentList.delegate = self
        monumentList.dataSource = self
        monumentList.register(UINib(nibName: monumentIdentifier, bundle: nil), forCellReuseIdentifier: monumentIdentifier)
    }
    
    override func getPresenter() -> BasePresenter<MonumentView>? {
        return presenter
    }
    
    override func registerListeners() {
        // Nothing to do yet
    }
    
    func navigateToMonumentItem(categoryId: String) {
        
    }
    
    func showMonumentList(monumentListItemView: MonumentMainItemDomain) {
        listItems.append(monumentListItemView)
        monumentList.reloadData()
    }
}
