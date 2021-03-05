package bec.c231371.asn2.sales.receipt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import bec.c231371.asn2.sales.SalesViewModel
import bec.c231371.asn2.databinding.FragmentScanBinding

class ReceiptFragment : Fragment() {

    private var binding: FragmentScanBinding? = null
    private val vm: SalesViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, state: Bundle?) =
        FragmentScanBinding.inflate(inflater, parent, false).run {
            root

            binding = this
            root
        }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}