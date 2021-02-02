package com.careradish.roastingboulevard.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.tools.DeveloperFreshData
import kotlinx.android.synthetic.main.fragment_init.view.*


/**
 * A simple [Fragment] subclass.
 * Use the [InitFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InitFragment : Fragment() {

    private lateinit var miRecycler: RecyclerView
    private lateinit var miAdapter: RecyclerView.Adapter<*>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var tempInflater= inflater.inflate(R.layout.fragment_init, container, false)
        miRecycler=tempInflater.findViewById(R.id.recyclerViewInit)
        miRecycler.setHasFixedSize(true)
        tempInflater.buttonInitStartOrder.setOnClickListener {
            DeveloperFreshData.UploadAllData()
        }

        return tempInflater
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InitFragment().apply {

            }
    }

    /*
    private fun DatosClientes(): List<Food> {
        val Lista: MutableList<Food> = ArrayList()
        Lista.add(
            Food(
                0, "Rey", "a", arrayListOf("patatas", "huevo", "leche"), arrayListOf(
                    "huevos",
                    "lacteos"
                ), R.mipmap.shrek, 0f
            )
        )
        Lista.add(
            Food(
                1, "a", "b", arrayListOf("a", "b", "c"), arrayListOf(
                    "a",
                    "v"
                ), R.mipmap.shrek, 0f
            )
        )
        return Lista
    }
*/
}