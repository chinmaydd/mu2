class CreatePerformances < ActiveRecord::Migration
  def change
    create_table :performances do |t|
      t.text :Description
      t.date :Date
      t.string :Link

      t.timestamps null: false
    end
  end
end
