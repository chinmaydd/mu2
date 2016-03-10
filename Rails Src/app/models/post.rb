class Post < ApplicationRecord
	attr_accessor :postable_id, :postable_type

	belongs_to :postable, polymorphic: true, optional: true
	has_many :instruments
	has_one :genre
	has_one :location
end
