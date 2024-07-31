//
//  ContentView.swift
//  iosApp
//
//  Created by Dahlke, Jan on 29.07.24.
//  Copyright © 2024 orgName. All rights reserved.
//

import shared
import SwiftUI

struct ContentView: View {
    
    
    @State private var isAboutOpen = false
    
    var body: some View {
        
        NavigationStack {
            ArticlesScreen(viewModel: .init())
                .toolbar {
                    ToolbarItem {
                        Button {
                            isAboutOpen = true
                        } label: {
                            Label("About", systemImage: "info.circle").labelStyle(.titleAndIcon)
                        }.popover(isPresented: $isAboutOpen) {
                            AboutScreen()
                        }
                    }
                }
        }
        
        
        
    }
    
    struct ContentView_Previews: PreviewProvider {
        static var previews: some View {
            ContentView()
        }
    }
    
}


