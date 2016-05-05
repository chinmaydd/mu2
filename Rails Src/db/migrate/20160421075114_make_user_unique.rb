class MakeUserUnique < ActiveRecord::Migration[5.0]
  def change
    add_index :users, :email, :unique => true
    add_index :musicians, :email, :unique => true
    add_index :bands, :email, :unique => true
  end
end