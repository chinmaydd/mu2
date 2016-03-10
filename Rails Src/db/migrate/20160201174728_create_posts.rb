class CreatePosts < ActiveRecord::Migration[5.0]
  def change
    create_table :posts do |t|
      t.text :title
      t.text :description
      t.date :start_date
      t.date :end_date
      t.boolean :is_satisfied
      t.references :postable, polymorphic: true, index: true

      t.timestamps
    end
  end
end
