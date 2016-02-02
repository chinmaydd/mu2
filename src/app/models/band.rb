class Band < ApplicationRecord
	has_many :performances, as: :performable
	has_many :musicians
	has_many :genres, as: :interest
	has_many :locations, as: :locatable
	has_many :posts, as: :postable

	serialize :favorite_artists, Array
	serialize :awards, Array
	serialize :songs, Array
	serialize :online_profiles, Array
end
