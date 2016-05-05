class AddInstrumentToMusicians < ActiveRecord::Migration[5.0]
  def change
    add_column  :musicians, :instrument, :string
    add_column :bands, :instrument, :string
  end
end
