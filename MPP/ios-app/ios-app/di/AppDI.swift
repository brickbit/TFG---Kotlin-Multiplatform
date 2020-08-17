//
//  AppDI.swift
//  ios-app
//
//  Created by Roberto García Romero on 15/08/2020.
//  Copyright © 2020 Roberto García Romero. All rights reserved.
//

import Foundation
import MultiPlatformLibrary

class AppDI {
    let local: LocalSource
    let network: NetworkSource
    let preferences: PreferencesSource
    let apiService: ApiService
    let repository: Repository
    let executor: Executor
    let errorHandler: ErrorHandler
    
    init() {
        apiService = ApiService()
        local = LocalSourceImp()
        network = NetworkSourceImp(apiService: apiService)
        preferences = PreferencesSourcesImpl()
        repository = RepositoryImp(network: self.network, local: self.local, preferences: self.preferences)
        executor = Executor()
        errorHandler = ErrorHandler()
    }

}
