//
//  ArticlesScreen.swift
//  iosApp
//
//  Created by Dahlke, Jan on 30.07.24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared


// this pattern shows how to use a kotlin state flow and translating it to an ios publisher
// https://developer.apple.com/documentation/combine/publisher

extension ArticlesScreen {
    @MainActor
    class ArticlesViewModelWrapper: ObservableObject {
        let articlesViewModel: ArticlesViewModel
        
        init() {
            articlesViewModel = ArticlesViewModel()
            articlesState = articlesViewModel.articlesState.value
        }
        
        @Published var articlesState : ArticlesState
        
        func startObserving() {
            Task {
                for await articlesS in articlesViewModel.articlesState {
                    self.articlesState = articlesS
                }
            }
        }
        
    }
}

struct ArticlesScreen: View {
    
    @ObservedObject private(set) var viewModel: ArticlesViewModelWrapper
    
    var body: some View {
        VStack {
            AppBar()
            
            if(viewModel.articlesState.loading) {
                Loader()
            }
            
            if let error = viewModel.articlesState.error {
                ErrorMessage(message: error)
            }
            
            if(!viewModel.articlesState.articles.isEmpty) {
                ScrollView{
                    LazyVStack(spacing: 10) {
                        ForEach(viewModel.articlesState.articles, id: \.self) { article in
                            ArticleItemView(article: article)
                        }
                    }
                }
            }
        }.onAppear() {
            self.viewModel.startObserving()
        }
    }
    
}

struct AppBar : View {
    var body : some View{
        Text("Articles")
            .font(.largeTitle)
            .fontWeight(.bold)
    }
}

struct ArticleItemView: View {
    var article: Article
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            AsyncImage(url: URL(string: article.imageURL)) { phase in
                if(phase.image != nil) {
                    phase.image!
                        .resizable()
                        .aspectRatio(contentMode: .fit)
                } else if phase.error != nil {
                    Text("Eror while loading image")
                } else {
                    ProgressView()
                }
            }
            Text(article.title)
                .font(.title)
                .fontWeight(.bold)
            Text(article.description_)
                .font(.subheadline)
            Text(article.date)
                .frame(maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/, alignment: .trailing)
                .foregroundStyle(.gray)
        }
        .padding(16)
    }
}

struct ErrorMessage: View {
    var message: String
    var body : some View {
        Text(message)
            .font(.title)
    }
}

struct Loader: View {
    var body: some View {
        ProgressView()
    }
}
