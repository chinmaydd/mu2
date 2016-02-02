class CreateJobs < ActiveRecord::Migration[5.0]
  def change
    create_table :jobs do |t|
      t.text :description
      t.date :start_date
      t.date :end_date

      t.timestamps
    end
  end
end
