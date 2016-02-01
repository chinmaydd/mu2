class CreateJobs < ActiveRecord::Migration
  def change
    create_table :jobs do |t|
      t.text :Description
      t.date :Start_Date
      t.date :End_Date
      t.string :duration

      t.timestamps null: false
    end
  end
end
