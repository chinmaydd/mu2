class CreateBands < ActiveRecord::Migration[5.0]
  def change
    create_table :bands do |t|
      t.string :name
      t.text :description
      t.integer :contact_number
      t.text :favorite_artists
      t.text :awards
      t.text :songs
      t.text :online_profiles

      t.references :postable, polymorphic: true, index: true

      t.timestamps
    end
  end
end
