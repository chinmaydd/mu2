class UsersController < ApplicationController
  before_action :set_user, only: [:update, :destroy]

  # GET /users
  def index
    @users = User.all

    render json: @users
  end

  # GET /users/:email
  def show
    temp = params[:id]
    @user = User.where(["email LIKE ?", "#{temp}%"]).first
    render json: @user
  end

  # POST /users
  def create
    @user = User.new
    @user.name = params["name"]
    @user.email = params["email"]

    if @user.save
      render json: @user, status: :created, location: @user
    else
      render json: @user.errors, status: :unprocessable_entity
    end
  end

  # PATCH/PUT /users/1
  def update
    if @user.update(user_params)
      render json: @user
    else
      render json: @user.errors, status: :unprocessable_entity
    end
  end

  # DELETE /users/1
  def destroy
    @user.destroy
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_user
    end

    # Only allow a trusted parameter "white list" through.
    def user_params
      params.permit(:name, :email)
    end
end
