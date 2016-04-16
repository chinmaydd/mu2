class Post < ApplicationRecord
	attr_accessor :postable_id, :postable_type

	belongs_to :postable, polymorphic: true, optional: true
end
