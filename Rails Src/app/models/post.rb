class Post < ApplicationRecord
	attr_accessor :postable_id, :postable_type, :title

	belongs_to :postable, polymorphic: true
	has_many :instruments
	has_one :genre
	has_one :location
end
