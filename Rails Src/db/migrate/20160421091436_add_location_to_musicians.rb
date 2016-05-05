class AddLocationToMusicians < ActiveRecord::Migration[5.0]
  def change
    add_column  :musicians, :location, :string
    add_column :bands, :location, :string
  end
end
