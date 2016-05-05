# encoding: UTF-8
# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# Note that this schema.rb definition is the authoritative source for your
# database schema. If you need to create the application database on another
# system, you should be using db:schema:load, not running all the migrations
# from scratch. The latter is a flawed and unsustainable approach (the more migrations
# you'll amass, the slower it'll run and the greater likelihood for issues).
#
# It's strongly recommended that you check this file into your version control system.

ActiveRecord::Schema.define(version: 20160421094154) do

  create_table "bands", force: :cascade do |t|
    t.string   "name"
    t.text     "description"
    t.string   "email"
    t.integer  "contact_number"
    t.text     "favorite_artists"
    t.text     "awards"
    t.text     "songs"
    t.text     "online_profiles"
    t.string   "postable_type"
    t.integer  "postable_id"
    t.datetime "created_at",       null: false
    t.datetime "updated_at",       null: false
    t.string   "instrument"
    t.string   "location"
  end

  add_index "bands", ["email"], name: "index_bands_on_email", unique: true
  add_index "bands", ["postable_type", "postable_id"], name: "index_bands_on_postable_type_and_postable_id"

  create_table "jobs", force: :cascade do |t|
    t.text     "description"
    t.date     "start_date"
    t.date     "end_date"
    t.datetime "created_at",  null: false
    t.datetime "updated_at",  null: false
  end

  create_table "musicians", force: :cascade do |t|
    t.string   "name"
    t.string   "email"
    t.text     "description"
    t.integer  "contact_number"
    t.string   "postable_type"
    t.integer  "postable_id"
    t.datetime "created_at",     null: false
    t.datetime "updated_at",     null: false
    t.string   "instrument"
    t.string   "location"
  end

  add_index "musicians", ["email"], name: "index_musicians_on_email", unique: true
  add_index "musicians", ["postable_type", "postable_id"], name: "index_musicians_on_postable_type_and_postable_id"

  create_table "posts", force: :cascade do |t|
    t.text     "title"
    t.text     "description"
    t.date     "start_date"
    t.date     "end_date"
    t.boolean  "is_satisfied"
    t.string   "postable_type"
    t.integer  "postable_id"
    t.datetime "created_at",    null: false
    t.datetime "updated_at",    null: false
    t.string   "location"
  end

  add_index "posts", ["postable_type", "postable_id"], name: "index_posts_on_postable_type_and_postable_id"

  create_table "users", force: :cascade do |t|
    t.string   "name"
    t.string   "email"
    t.string   "postable_type"
    t.integer  "postable_id"
    t.datetime "created_at",    null: false
    t.datetime "updated_at",    null: false
  end

  add_index "users", ["email"], name: "index_users_on_email", unique: true
  add_index "users", ["postable_type", "postable_id"], name: "index_users_on_postable_type_and_postable_id"

end
