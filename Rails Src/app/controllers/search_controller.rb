class SearchController < ApplicationController

  # GET /search/<string>
  def index
    query = params[:string]
    @posts = Post.where("description like ?", "%#{query}%")
    @search_results = @posts.map{ |p|
        j = JSON.parse(p.to_json)
        j["user"] = JSON.parse(p.postable.to_json)
        j
    }
    render json: {posts: @search_results}
  end
end