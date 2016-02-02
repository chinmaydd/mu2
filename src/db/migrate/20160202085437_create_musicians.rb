class CreateMusicians < ActiveRecord::Migration[5.0]
  def change
    create_table :musicians do |t|
      t.string :name
      t.text :description
      t.integer :contact_number
      t.references :performable, polymorphic: true, index: true
      t.references :postable, polymorphic: true, index: true
      t.references :locatable, polymorphic: true, index: true

      t.timestamps
    end
  end
end
