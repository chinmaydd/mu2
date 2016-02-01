class CreateGenres < ActiveRecord::Migration
  def change
    create_table :genres do |t|
      t.string :Name

      t.timestamps null: false
    end
  end
end
