//
//  ViewController.swift
//  MyApp
//
//  Created by Sergio Casero Hernández on 28/04/2020.
//  Copyright © 2020 Tempos21. All rights reserved.
//

import UIKit
import MultiPlatformLibrary

open class BaseViewController<V: View>: UIViewController, View {

    lazy var progress = UIActivityIndicatorView()
    lazy var errorDialog = UIAlertController()
    
    open override func viewDidLoad() {
        super.viewDidLoad()
     
        let di  = AppDI()
        
        progress.center.x = UIScreen.main.bounds.width / 2
        progress.center.y = UIScreen.main.bounds.height / 2
        
        progress.activityIndicatorViewStyle = .whiteLarge
        progress.color = UIColor.red
        
        view.addSubview(progress)
        
        initializeUI()
        registerListeners()
        getPresenter()?.attach()
    }
    
    open override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
    }
    
    deinit {
        getPresenter()?.detach()
    }
    
    func initializeUI() {}
    func registerListeners() {}
    open func getPresenter() -> BasePresenter<V>? { nil }
    
    public func showProgress() {
        progress.startAnimating()
    }
    
    public func hideProgress() {
        progress.stopAnimating()
    }
    
    public func showError(error: String) {
        let alert = UIAlertController()
        alert.message = error
        alert.title = "Error"
        
        let actionDismiss = UIAlertAction(
            title: "Aceptar",
            style: .default,
            handler: nil
        )
        
        alert.addAction(actionDismiss)
        
        self.present(alert, animated: true, completion: nil)
    }
    
    public func showRetry(error: String, f: @escaping () -> Void) {
        let alert = UIAlertController()
        alert.message = error
        alert.title = "Error"
    
        let action = UIAlertAction(
            title: "Reintentar",
            style: .default,
            handler: { _ in f() }
        )
        
        let actionDismiss = UIAlertAction(
            title: "Aceptar",
            style: .default,
            handler: nil
        )
    
        alert.addAction(actionDismiss)
        alert.addAction(action)
    
        self.present(alert, animated: true, completion: nil)
    }
    
    public func showMessage(message: String) {
        
    }
}


