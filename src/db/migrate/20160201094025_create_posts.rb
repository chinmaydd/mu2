class CreatePosts < ActiveRecord::Migration
  def change
    create_table :posts do |t|
      t.text :Description
      t.date :Start_Date
      t.date :End_Date
      t.boolean :is_satisfied

      t.timestamps null: false
    end
  end
end
