package bec.c231371.poss.sales.receipt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import bec.c231371.poss.databinding.FragmentReceiptBinding
import bec.c231371.poss.sales.SaleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReceiptFragment : Fragment() {

    private var binding: FragmentReceiptBinding? = null
    private val vm: SaleViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, state: Bundle?): View =
        FragmentReceiptBinding.inflate(inflater, parent, false).run {
            // Setup UI
            // TODO

            // Keep binding; return view
            binding = this
            root
        }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}