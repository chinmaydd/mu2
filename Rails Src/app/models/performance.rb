class Performance < ApplicationRecord
	attr_accessor :performable_id, :performable_type

    belongs_to :performable, polymorphic: true
    has_one :location
end
