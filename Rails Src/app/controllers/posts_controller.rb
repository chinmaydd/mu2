class PostsController < ApplicationController
  before_action :set_post, only: [:show, :update, :destroy]

  # GET /posts
  def index
    @posts = Post.all
    @search_results = @posts.map{ |p|
      j = JSON.parse(p.to_json)
      j["user"] = JSON.parse(p.postable.to_json)
      j
    }
    puts @search_results
    render json: {posts: @search_results}
  end

  def search
    @posts = Post.all
    s = params["string"]
    @search_results = []
    @posts.map{ |p|
      if p.title.include? s or p.description.include? s
        j = JSON.parse(p.to_json)
        j["user"] = JSON.parse(p.postable.to_json)
        @search_results << j
      else
        next
      end
    }
    render json: {posts: @search_results}
  end


  # GET /posts/1
  def show
    render json: @post
  end

  # POST /posts
  def create
    @post = Post.new(post_params)
    @post.location = params["location"]

    if User.where(:name => params["user"]).first.nil?
      author = User.new
      author.email = params["email"]
      author.name = params["user"]
      if author.save
        @post.postable = author
      else
        render json: author.errors, status: :unprocessable_entity
      end
    else
      author = User.where(:name => params["user"]).first
      @post.postable = author
    end

    if @post.save
      render json: @post, status: :created, location: @post
    else
      render json: @post.errors, status: :unprocessable_entity
    end
  end

  # PATCH/PUT /posts/1
  def update
    if @post.update(post_params)
      render json: @post
    else
      render json: @post.errors, status: :unprocessable_entity
    end
  end

  # DELETE /posts/1
  def destroy
    @post.destroy
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_post
      @post = Post.find(params[:id])
    end

    # Only allow a trusted parameter "white list" through.
    def post_params
      params.require(:post).permit(:title, :user, :email, :description, :start_date, :end_date, :is_satisfied)
    end
end
