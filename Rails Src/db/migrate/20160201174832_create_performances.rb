class CreatePerformances < ActiveRecord::Migration[5.0]
  def change
    create_table :performances do |t|
      t.text :description
      t.date :date
      t.string :link

      t.timestamps
    end
  end
end
