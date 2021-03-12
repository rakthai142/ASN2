package bec.c231371.poss.sales.scan

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import bec.c231371.poss.databinding.FragmentScanBinding
import bec.c231371.poss.sales.SaleViewModel

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScanFragment : Fragment() {

    private var binding: FragmentScanBinding? = null
    private val vm: SaleViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, state: Bundle?): View =
        FragmentScanBinding.inflate(inflater, parent, false).run {
            // Setup UI
            val adapter = ProductListAdapter(emptyList()) { pq ->
//               ProductEditFragment.newInstance(pq.barcode, pq.name, pq.priceSatang, pq.category)
//                   .show(childFragmentManager, ProductEditFragment.TAG)
            }
            scanProducts.adapter = adapter

            vm.basket.observe(viewLifecycleOwner) {
                adapter.update(it)
            }

            scanBarcodeManualAdd.setOnClickListener {
                vm.incProduct(scanBarcodeManual.text.toString())
                scanBarcodeManual.text.clear()
            }

            scanBarcodeManual.setOnEditorActionListener { _, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_SEND || event?.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_ENTER) {
                    vm.incProduct(scanBarcodeManual.text.toString())
                    scanBarcodeManual.text.clear()
                    true
                } else false
            }

            // Keep binding; return view
            binding = this
            root
        }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}