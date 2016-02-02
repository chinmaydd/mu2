require 'test_helper'

class InstrumentsControllerTest < ActionDispatch::IntegrationTest
  setup do
    @instrument = instruments(:one)
  end

  test "should get index" do
    get instruments_url
    assert_response :success
  end

  test "should create instrument" do
    assert_difference('Instrument.count') do
      post instruments_url, params: { instrument: { exp_level: @instrument.exp_level, name: @instrument.name, type: @instrument.type } }
    end

    assert_response 201
  end

  test "should show instrument" do
    get instrument_url(@instrument)
    assert_response :success
  end

  test "should update instrument" do
    patch instrument_url(@instrument), params: { instrument: { exp_level: @instrument.exp_level, name: @instrument.name, type: @instrument.type } }
    assert_response 200
  end

  test "should destroy instrument" do
    assert_difference('Instrument.count', -1) do
      delete instrument_url(@instrument)
    end

    assert_response 204
  end
end
