//
//  AboutScreen.swift
//  iosApp
//
//  Created by Dahlke, Jan on 29.07.24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct AboutScreen: View {
    
    @Environment(\.dismiss)
    private var dismiss
    
    var body: some View {
        NavigationStack {
            AboutListView()
                .navigationTitle("About Device")
                .toolbar{
                    ToolbarItem(placement: .primaryAction) {
                        Button {
                            dismiss()
                        } label: {
                            Text("Done")
                                .bold()
                        }
                    }
                }
        }
    }
}

#Preview {
    AboutScreen()
}
