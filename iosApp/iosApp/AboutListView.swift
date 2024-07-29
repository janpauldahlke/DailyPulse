//
//  AboutListView.swift
//  iosApp
//
//  Created by Dahlke, Jan on 29.07.24.
//  Copyright © 2024 orgName. All rights reserved.
//

import shared
import SwiftUI


struct AboutListView: View {
    private struct RowItem: Hashable {
        let title: String
        let subtitle: String
    }
    
    private let items : [RowItem] = {
        let platform = Platform()
        platform.logSystemInfo()
        
        var result : [RowItem] = [
            .init(
                title: "Operating System", subtitle: "\(platform.osName) \(platform.osVersion)"
            ),
            .init(
                title: "Device", subtitle: platform.deviceModel
            ),
            .init(
                title: "ScreenDensity", subtitle: "@\(platform.density)"
            )
            
        ]
        return result
        
    }()
    
    var body : some View {
        List {
            ForEach(items, id: \.self) { item in
                VStack(alignment: .leading) {
                    Text(item.title)
                        .font(.footnote)
                        .foregroundColor(.black)
                        .foregroundStyle(.secondary)
                    Text(item.subtitle)
                        .font(.body)
                        .foregroundColor(/*@START_MENU_TOKEN@*/.blue/*@END_MENU_TOKEN@*/)
                        .foregroundStyle(.primary)
                }
                .padding(.vertical, 4)
            }
        }
    }
 
}


#Preview {
    AboutListView()
}
