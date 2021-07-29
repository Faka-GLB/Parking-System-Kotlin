package com.onboarding.parkingsystemkotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.onboarding.parkingsystemkotlin.R
import com.onboarding.parkingsystemkotlin.databinding.ItemReservationBinding
import com.onboarding.parkingsystemkotlin.entity.Reservation

class ReservationAdapter(private val list: List<Reservation>) : RecyclerView.Adapter<ReservationAdapter.ViewHolder>() {

    override fun getItemCount() = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemReservationBinding.inflate(LayoutInflater.from(parent.context), parent, false), parent.context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class ViewHolder(private val binding: ItemReservationBinding, private val context: Context) : RecyclerView.ViewHolder(binding.root) {
        fun bind(reservation: Reservation) {
            binding.textViewItemReservationParkingLot.text =
                context.getString(R.string.reservation_item_text_view_parking_lot, reservation.getParkingLot())
            binding.textViewItemReservationStartDate.text =
                context.getString(R.string.reservation_item_text_view_start_date, reservation.getStartDateString())
            binding.textViewItemReservationEndDate.text =
                context.getString(R.string.reservation_item_text_view_end_date, reservation.getEndDateString())
        }
    }
}
