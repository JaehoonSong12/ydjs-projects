using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class BallCollectable : MonoBehaviour
{
    private void OnTriggerEnter(Collider c)
    {
        if (c.attachedRigidbody != null)
        {
            BallCollector bc = c.attachedRigidbody.gameObject.GetComponent<BallCollector>();
            if (bc != null)
            {
                bc.ReceiveBall();
                EventManager.TriggerEvent<BombBounceEvent, Vector3>(c.transform.position);
                Destroy(this.gameObject);

                // Trigger the pink effect
                PinkEffectController effectController = c.attachedRigidbody.gameObject.GetComponent<PinkEffectController>();
                if (effectController != null)
                {
                    effectController.TriggerPinkEffect(c.transform.position);
                }
            }
        }
    }


        // Start is called before the first frame update
        void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }
}
