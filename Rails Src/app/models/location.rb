class Location < ApplicationRecord
	attr_accessor :locatable_id, :locatable_type

	belongs_to :locatable, polymorphic: true
end
