class MusiciansController < ApplicationController
  before_action :set_musician, only: [:show, :update, :destroy]

  # GET /musicians
  def index
    @musicians = Musician.all

    render json: @musicians
  end

  # GET /musicians/1
  def show
    render json: @musician
  end

  # POST /musicians
  def create
    @musician = Musician.new(musician_params)

    if @musician.save
      render json: @musician, status: :created, location: @musician
    else
      render json: @musician.errors, status: :unprocessable_entity
    end
  end

  # PATCH/PUT /musicians/1
  def update
    if @musician.update(musician_params)
      render json: @musician
    else
      render json: @musician.errors, status: :unprocessable_entity
    end
  end

  # DELETE /musicians/1
  def destroy
    @musician.destroy
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_musician
      @musician = Musician.find(params[:id])
    end

    # Only allow a trusted parameter "white list" through.
    def musician_params
      params.require(:musician).permit(:name, :description, :contact_number)
    end
end
