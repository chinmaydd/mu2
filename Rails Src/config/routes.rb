Rails.application.routes.draw do
  resources :bands
  resources :musicians
  resources :instruments
  resources :jobs
  resources :performances
  resources :posts
  resources :users

  post "/search/:string" => "search#index"
  # For details on the DSL available within this file, see http://guides.rubyonrails.org/routing.html

  # Serve websocket cable requests in-process
  # mount ActionCable.server => '/cable'
end
